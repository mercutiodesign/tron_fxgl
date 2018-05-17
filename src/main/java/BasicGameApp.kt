/*
 * FXGL - JavaFX Game Library. The MIT License (MIT).
 * Copyright (c) AlmasB (almaslvl@gmail.com).
 * See LICENSE for details.
 */


import com.almasb.fxgl.app.GameApplication
import com.almasb.fxgl.entity.Entities
import com.almasb.fxgl.entity.Entity
import com.almasb.fxgl.input.UserAction
import com.almasb.fxgl.settings.GameSettings
import javafx.application.Application
import javafx.scene.input.KeyCode
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle
import javafx.scene.text.Text

/**
 * This is a basic FXGL game application tutorial.
 *
 * @author Almas Baimagambetov (AlmasB) (almaslvl@gmail.com)
 */
class BasicGameApp : GameApplication() {

    private var player: Entity? = null

    override fun initSettings(settings: GameSettings) {
        settings.width = 600
        settings.height = 600
        settings.title = "Basic Game App"
        settings.version = "0.1"
    }

    override fun initInput() {
        val input = input // get input service

        input.addAction(object : UserAction("Move Right") {
            override fun onAction() {
                player!!.translateX(5.0) // move right 5 pixels
                gameState.increment("pixelsMoved", +5)
            }
        }, KeyCode.D)

        input.addAction(object : UserAction("Move Left") {
            override fun onAction() {
                player!!.translateX(-5.0) // move left 5 pixels
                gameState.increment("pixelsMoved", +5)
            }
        }, KeyCode.A)

        input.addAction(object : UserAction("Move Up") {
            override fun onAction() {
                player!!.translateY(-5.0) // move up 5 pixels
                gameState.increment("pixelsMoved", +5)
            }
        }, KeyCode.W)

        input.addAction(object : UserAction("Move Down") {
            override fun onAction() {
                player!!.translateY(5.0) // move down 5 pixels
                gameState.increment("pixelsMoved", +5)
            }
        }, KeyCode.S)
    }

    override fun initGameVars(vars: MutableMap<String, Any>?) {
        vars!!["pixelsMoved"] = 0
    }

    override fun initGame() {
        player = Entities.builder()
                .at(300.0, 300.0)
                .viewFromNode(Rectangle(25.0, 25.0, Color.BLUE))
                .buildAndAttach(gameWorld)
    }

    override fun initUI() {
        val textPixels = Text()
        textPixels.translateX = 50.0 // x = 50
        textPixels.translateY = 100.0 // y = 100

        textPixels.textProperty().bind(gameState.intProperty("pixelsMoved").asString())

        gameScene.addUINode(textPixels) // add to the scene graph
    }


    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Application.launch(BasicGameApp::class.java, *args)
        }
    }
}
