package com.example.digikalatestononline.navigation

sealed class Screen(val route: String) {

    object Splash : Screen("splash_screen")
    object Home : Screen("home_screen")
    object Category : Screen("category_screen")
    object Basket : Screen("basket_screen")
    object Profile : Screen("profile_screen")
    object WebView : Screen("WebView_screen")
    object CheckOut : Screen("CheckOut_screen")
    object ConfirmPurchase : Screen("confirm_purchase_screen")
    object ProductDetail : Screen("ProductDetail_screen")
    object ProductDescription : Screen("ProductDescription_screen")
    object ProductTechnicalFeaturesScreen : Screen("ProductTechnicalFeatures_screen")
    object NewComment : Screen("NewComment_screen")
    object AllComment : Screen("AllComment_screen")

    fun withArgs(vararg args : Any) : String {
        return buildString {
            append(route)
            args.forEach {
                append("/$it")
            }
        }
    }

}