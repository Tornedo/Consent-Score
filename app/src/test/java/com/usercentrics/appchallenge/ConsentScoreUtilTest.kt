package com.usercentrics.appchallenge

import com.usercentrics.appchallenge.util.DataCostInfo
import org.junit.Test

import org.junit.Assert.*

/**
 * ConsentScoreUtilTest unit test, check service cost table
 */
class ConsentScoreUtilTest {

    @Test
    fun ipAddressCostCheck() {
        val ipAddressCostCheck = DataCostInfo.IP_ADDRESS.cost
        assertEquals(ipAddressCostCheck, 2 )
    }

    @Test
    fun ipAddressServiceNameCheck() {
        val ipAddressServiceNameCheck = DataCostInfo.IP_ADDRESS.dataBeingCollected
        assertEquals(ipAddressServiceNameCheck, "IP address" )
    }

    @Test
    fun firstNameCostCheck() {
        val firstNameCostCheck = DataCostInfo.FIRST_NAME.cost
        assertEquals(firstNameCostCheck, 6 )
    }

    @Test
    fun firstNameServiceNameCheck() {
        val ipAddressCostCheck = DataCostInfo.FIRST_NAME.dataBeingCollected
        assertEquals(ipAddressCostCheck, "First name" )
    }
}