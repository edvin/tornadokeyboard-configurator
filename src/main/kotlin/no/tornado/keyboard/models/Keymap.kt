package no.tornado.keyboard.models

import javafx.beans.property.SimpleListProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.*
import javax.json.JsonObject


class Keymap : JsonModel {
    val nameProperty = SimpleStringProperty()
    var name by nameProperty

    val layersProperty = SimpleListProperty<Layer>()
    var layers by layersProperty

    override fun updateModel(json: JsonObject) {
        with(json) {
            name = string("name")
            layers = getJsonArray("layers").toModel()
            layers?.forEach { it.keymap = this@Keymap }
        }
    }

}

class KeymapModel(keymap: Keymap) : ItemViewModel<Keymap>(keymap) {
    fun removeLayer(layer: Layer) {
        item.layers.remove(layer)
    }

    fun addLayer() = Layer().apply {
        name = "New layer"
        keymap = item
        item.layers.add(this)
    }
}

