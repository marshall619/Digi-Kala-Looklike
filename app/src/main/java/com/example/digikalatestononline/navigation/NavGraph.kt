package com.example.digikalatestononline.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.digikalatestononline.ui.screens.basket.BasketScreen
import com.example.digikalatestononline.ui.screens.category.CategoryScreen
import com.example.digikalatestononline.ui.screens.checkOut.CheckOutScreen
import com.example.digikalatestononline.ui.screens.checkOut.ConfirmPurchaseScreen
import com.example.digikalatestononline.ui.screens.profile.ProfileScreen
import com.example.digikalatestononline.ui.screens.splash.SplashScreen
import com.example.digikalatestononline.ui.screens.home.HomeScreen
import com.example.digikalatestononline.ui.screens.home.WebPageScreenSection
import com.example.digikalatestononline.ui.screens.product_detail.AllCommentProductScreen
import com.example.digikalatestononline.ui.screens.product_detail.NewCommentScreen
import com.example.digikalatestononline.ui.screens.product_detail.ProductDescriptionScreen
import com.example.digikalatestononline.ui.screens.product_detail.ProductDetailScreen
import com.example.digikalatestononline.ui.screens.product_detail.ProductPriceChartScreen
import com.example.digikalatestononline.ui.screens.product_detail.ProductTechnicalFeaturesScreen


@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {

        composable(route = Screen.Splash.route) {
            SplashScreen(navController = navController)
        }

        composable(route = Screen.Home.route) {
            HomeScreen(navController = navController)
        }

        composable(route = Screen.Category.route) {
            CategoryScreen(navController = navController)
        }

        composable(route = Screen.Basket.route) {
            BasketScreen(navController = navController)
        }

        composable(route = Screen.Profile.route) {
            ProfileScreen(navController = navController)
        }
        composable(route = Screen.CheckOut.route) {
            CheckOutScreen(navController = navController)
        }

        composable(
            route = Screen.WebView.route + "?url={url}",
            arguments = listOf(navArgument("url"){
                type = NavType.StringType
                defaultValue = ""
                nullable = true
            })
        ){
            val url = it.arguments?.getString("url")
            url?.let {
                WebPageScreenSection(navHostController = navController, url = url)
            }
        }

        composable(route = Screen.ConfirmPurchase.route + "/{orderId}/{orderPrice}",
            arguments = listOf(
                navArgument("orderId"){
                    type = NavType.StringType
                    defaultValue = " "
                    nullable = true
                },
                navArgument("orderPrice"){
                    type = NavType.StringType
                    defaultValue = " "
                    nullable = true
                }
            )
        ) {
            it.arguments!!.getString("orderId")?.let { orderId->
                it.arguments!!.getString("orderPrice")?.let { orderPrice->
                    ConfirmPurchaseScreen(
                        navController = navController,
                        orderId = orderId,
                        orderPrice = orderPrice
                    )
                }
            }
        }

        composable(route = Screen.ProductDetail.route + "/{productId}",
            arguments = listOf(
                navArgument("productId") {
                    type = NavType.StringType
                    defaultValue = " "
                    nullable = true
                }
            )
        ) {
            it.arguments!!.getString("productId")?.let { productId ->
                ProductDetailScreen(
                    navController = navController,
                    productId = productId
                )
            }

        }

        composable(route = Screen.ProductDescription.route + "/{description}",
            arguments = listOf(
                navArgument("description") {
                    type = NavType.StringType
                    defaultValue = " "
                    nullable = true
                }
            )
        ) {
            it.arguments!!.getString("description")?.let { description ->
                ProductDescriptionScreen(
                    navController = navController,
                    description = description
                )
            }

        }

        composable(route = Screen.ProductTechnicalFeaturesScreen.route + "/{technicalFeatures}",
            arguments = listOf(
                navArgument("technicalFeatures") {
                    type = NavType.StringType
                    defaultValue = " "
                    nullable = true
                }
            )
        ) {
            it.arguments!!.getString("technicalFeatures")?.let { technicalFeatures ->
                ProductTechnicalFeaturesScreen(
                    navController = navController,
                    jsonString = technicalFeatures
                )
            }

        }

        composable(route = Screen.ProductDetailChart.route + "?jsonString={jsonString}",
            arguments = listOf(
                navArgument("jsonString") {
                    type = NavType.StringType
                    defaultValue = " "
                    nullable = true
                }
            )
        ){
            it.arguments!!.getString("jsonString")?.let {jsonString ->
                ProductPriceChartScreen(navController = navController, jsonString = jsonString)
            }
        }

        composable(route = Screen.NewComment.route + "?productId={productId}?productName={productName}?imageUrl={imageUrl}",
            arguments = listOf(
                navArgument("productId"){
                    type = NavType.StringType
                    defaultValue = " "
                    nullable = true
                },
                navArgument("productName"){
                    type = NavType.StringType
                    defaultValue = " "
                    nullable = true
                },
                navArgument("imageUrl"){
                    type = NavType.StringType
                    defaultValue = " "
                    nullable = true
                }
            )
        ) {
            it.arguments!!.getString("productId")?.let { productId->
                it.arguments!!.getString("productName")?.let { productName->
                    it.arguments!!.getString("imageUrl")?.let { imageUrl->
                        NewCommentScreen(
                            navController = navController,
                            productId = productId,
                            productName = productName,
                            imageUrl = imageUrl
                        )
                    }
                }
            }
        }

        composable(route = Screen.AllComment.route + "/{productId}/{commentsCount}",
            arguments = listOf(
                navArgument("productId") {
                    type = NavType.StringType
                    defaultValue = " "
                    nullable = true
                },
                navArgument("commentsCount") {
                    type = NavType.StringType
                    defaultValue = " "
                    nullable = true
                }
            )
        ) {
            it.arguments!!.getString("productId")?.let { productId ->
                it.arguments!!.getString("commentsCount")?.let { commentsCount ->
                    AllCommentProductScreen(
                        navController = navController,
                        productId = productId,
                        commentsCount = commentsCount
                    )
                }
            }

        }
    }
}