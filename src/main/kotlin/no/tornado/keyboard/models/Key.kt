package no.tornado.keyboard.models

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleListProperty
import javafx.beans.property.SimpleObjectProperty
import tornadofx.*
import javax.json.JsonObject

enum class LayerToggleType(val actionName: String) {
    Toggle("ACTION_LAYER_TOGGLE"),
    Momentary("ACTION_LAYER_MOMENTARY")
}

class Key(code: KeyCode? = null) : JsonModel {
    val modifiersProperty = SimpleListProperty<Key>()
    var modifiers by modifiersProperty

    val layerProperty = SimpleIntegerProperty()
    var layer: Int? by layerProperty

    val layerToggleTypeProperty = SimpleObjectProperty<LayerToggleType>()
    var layerToggleType by layerToggleTypeProperty

    val codeProperty = SimpleObjectProperty<KeyCode>(code)
    var code by codeProperty

    override fun updateModel(json: JsonObject) {
        with(json) {
            if (containsKey("modifiers"))
                modifiers = getJsonArray("modifiers").toModel()

            if (containsKey("layer"))
                layer = getInt("layer")

            if (containsKey("layerToggleType"))
                layerToggleType = LayerToggleType.valueOf(getString("layerToggleType"))

            if (containsKey("code"))
                code = KeyCode.valueOf(getString("code"))
        }
    }
}