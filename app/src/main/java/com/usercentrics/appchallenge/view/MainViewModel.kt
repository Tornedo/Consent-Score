package com.usercentrics.appchallenge.view

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.usercentrics.appchallenge.data.ConsentedService
import com.usercentrics.appchallenge.util.DataCostInfo
import com.usercentrics.sdk.UsercentricsCMPData
import com.usercentrics.sdk.UsercentricsConsentUserResponse
import org.koin.core.KoinComponent

class MainViewModel : ViewModel(), KoinComponent {

    private val consentScoreLiveData: MutableLiveData<String> by lazy(
        LazyThreadSafetyMode.NONE,
        initializer = { MutableLiveData<String>() })

    fun getConsentScore(): LiveData<String> {
        return consentScoreLiveData
    }

    fun calculateServiceCost(
        userResponse: UsercentricsConsentUserResponse,
        data: UsercentricsCMPData
    ) {
        var totalCost: Double = 0.0

        var concentedHashMap: HashMap<String, ConsentedService> =
            HashMap<String, ConsentedService>()

        /*
            store the consented service with their consented status and template id
         */
        userResponse.consents.forEach { consent ->
            val consentItem = ConsentedService(consent.status, consent.dataProcessor)
            concentedHashMap[consent.templateId] = consentItem
        }

        data.services.forEach { it ->
            var cost = 0.0

            if (concentedHashMap.containsKey(it.templateId)) {
                val consentItem = concentedHashMap[it.templateId]
                if (consentItem != null) {
                    if (consentItem.status) {
                        it.dataCollectedList.forEach { dataItem ->
                            for (item in DataCostInfo.values()) {
                                if (dataItem == item.dataBeingCollected) {
                                    cost += item.cost
                                }
                            }
                        }
                        /*
                            check and apply BankingSnoopy which is add cost by 10%
                         */
                        if (it.dataCollectedList.contains(DataCostInfo.PURCHASE_ACTIVITY.dataBeingCollected)
                            && it.dataCollectedList.contains(DataCostInfo.BANK_DETAILS.dataBeingCollected)
                            && it.dataCollectedList.contains(DataCostInfo.CREDIT_AND_DEBIT_CARD_NUMBER.dataBeingCollected)
                        ) {
                            var applyBankingSnoopy = (10.toFloat() * cost) / 100
                            cost += applyBankingSnoopy
                        }
                        /*
                           check and apply CareEnabled which is add cost by 27%
                         */
                        if (it.dataCollectedList.contains(DataCostInfo.GEOGRAPHIC_LOCATION.dataBeingCollected)
                            && it.dataCollectedList.contains(DataCostInfo.SEARCH_TERMS.dataBeingCollected)
                            && it.dataCollectedList.contains(DataCostInfo.NUMBER_OF_PAGE_VIEWS.dataBeingCollected)
                        ) {
                            var applyCareEnabled = (27.toFloat() * cost) / 100
                            cost += applyCareEnabled
                        }
                        /*
                           check and apply Good Citizen which is reduce cost by 10%
                        */
                        if (cost > 0 && it.dataCollectedList.size <= 4) {
                            var applyGoodCitizenEnabled = (10.toFloat() * cost) / 100
                            cost -= applyGoodCitizenEnabled

                        }

                        totalCost += cost
                        /*
                            Logging the each service name and cost
                         */
                        Log.e("Service name:  ${consentItem.name}", "cost = $cost")
                    }
                }
            }

        }

        /*
            convert total cost into string into .2 decimal point
         */
        var totalCostStr: String? = "%.2f".format(totalCost)
        consentScoreLiveData.postValue(totalCostStr)
    }
}