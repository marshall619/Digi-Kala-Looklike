package com.example.digikalatestononline.navigation

sealed class Screen(val route: String) {

    data object Splash : Screen("splash_screen")
    data object Home : Screen("home_screen")
    data object Category : Screen("category_screen")
    data object Basket : Screen("basket_screen")
    data object Profile : Screen("profile_screen")
    data object WebView : Screen("WebView_screen")
    data object CheckOut : Screen("CheckOut_screen")
    data object ConfirmPurchase : Screen("confirm_purchase_screen")
    data object ProductDetail : Screen("ProductDetail_screen")
    data object ProductDescription : Screen("ProductDescription_screen")
    data object ProductTechnicalFeaturesScreen : Screen("ProductTechnicalFeatures_screen")
    data object NewComment : Screen("NewComment_screen")
    data object AllComment : Screen("AllComment_screen")
    data object productDetailChart : Screen("AllComment_screen")

    fun withArgs(vararg args : Any) : String {
        return buildString {
            append(route)
            args.forEach {
                append("/$it")
            }
        }
    }

}