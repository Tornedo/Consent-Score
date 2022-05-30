package com.usercentrics.appchallenge.util

/*
    Cost of each service
 */
enum class DataCostInfo {
    CONFIGURATION_OF_APP_SETTING(1, "Configuration of app setting"),
    IP_ADDRESS(2, "IP address"),
    USER_BEHAVIOUR(2, "User behaviour"),
    USER_AGENT(3, "User agent"),
    APP_CRASHES(-2, "App crashes"),
    BROWSER_INFORMATION(3, "Browser information"),
    CREDIT_AND_DEBIT_CARD_NUMBER(4, "Credit and debit card number"),
    FIRST_NAME(6, "First name"),
    GEOGRAPHIC_LOCATION(7, "Geographic location"),
    DATE_AND_TIME_OF_VISIT(1, "Date and time of visit"),
    ADVERTISING_IDENTIFIER(2, "Advertising identifier"),
    BANK_DETAILS(5, "Bank details"),
    PURCHASE_ACTIVITY(6, "Purchase activity"),
    INTERNET_SERVICE_PROVIDER(4, "Internet service provider"),
    JAVASCRIPT_SUPPORT(-1, "JavaScript support"),
    SEARCH_TERMS(0, "Search terms"),
    NUMBER_OF_PAGE_VIEWS(0, "Number of page views");

    //  properties with default values
    var cost: Int = 0
    var dataBeingCollected: String = ""

    constructor()

    // constructors
    constructor(
        cost: Int,
        dataBeingCollected: String
    ) {
        this.cost = cost
        this.dataBeingCollected = dataBeingCollected
    }
}