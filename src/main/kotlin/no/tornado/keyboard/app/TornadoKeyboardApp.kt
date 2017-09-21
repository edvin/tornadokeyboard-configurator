package no.tornado.keyboard.app

import javafx.stage.Stage
import no.tornado.keyboard.models.Keymap
import no.tornado.keyboard.models.KeymapModel
import no.tornado.keyboard.views.KeymapEditor
import tornadofx.*
import javax.json.Json

class TornadoKeyboardApp : App(KeymapEditor::class, Styles::class) {
    override fun start(stage: Stage) {
        val keymap = Keymap()
        val json = Json.createReader(Keymap::class.java.getResourceAsStream("/default-keymap.json")).readObject()
        keymap.updateModel(json)
        scope.set(KeymapModel(keymap))
        super.start(stage)
    }
}