package no.tornado.keyboard.models

enum class KeyCodeGroup {
    Other,
    Puncuation,
    ShiftedPunctuation,
    Digits,
    Spacing,
    System,
    Mouse,
    Numpad,
    Navigation,
    Modifiers,
    Media,
    LayerSwitching,
    FnKeys,
    DualFunctionKeys,
    Letters
}

enum class KeyCode(val description: String, val group: KeyCodeGroup = KeyCodeGroup.Other, val isModifier: Boolean = false, val isSelectable: Boolean = true) {
    F("FN", isSelectable = false),
    LCTL("Left Control", isModifier = true, isSelectable = false),
    RCTL("Right Control", isModifier = true, isSelectable = false),
    LALT("Left Alt", isModifier = true, isSelectable = false),
    RALT("Right Alt", isModifier = true, isSelectable = false),
    LSHFT("Left Shift", isModifier = true, isSelectable = false),
    RSHFT("Right Shift", isModifier = true, isSelectable = false),

    KC_TRNS("", isSelectable = true),
    KC_NO("", isSelectable = false),
    KC_A("A", group = KeyCodeGroup.Letters),
    KC_B("B", group = KeyCodeGroup.Letters),
    KC_C("C", group = KeyCodeGroup.Letters),
    KC_D("D", group = KeyCodeGroup.Letters),
    KC_E("E", group = KeyCodeGroup.Letters),
    KC_F("F", group = KeyCodeGroup.Letters),
    KC_G("G", group = KeyCodeGroup.Letters),
    KC_H("H", group = KeyCodeGroup.Letters),
    KC_I("I", group = KeyCodeGroup.Letters),
    KC_J("J", group = KeyCodeGroup.Letters),
    KC_K("K", group = KeyCodeGroup.Letters),
    KC_L("L", group = KeyCodeGroup.Letters),
    KC_M("M", group = KeyCodeGroup.Letters),
    KC_N("N", group = KeyCodeGroup.Letters),
    KC_O("O", group = KeyCodeGroup.Letters),
    KC_P("P", group = KeyCodeGroup.Letters),
    KC_Q("Q", group = KeyCodeGroup.Letters),
    KC_R("R", group = KeyCodeGroup.Letters),
    KC_S("S", group = KeyCodeGroup.Letters),
    KC_T("T", group = KeyCodeGroup.Letters),
    KC_U("U", group = KeyCodeGroup.Letters),
    KC_V("V", group = KeyCodeGroup.Letters),
    KC_W("W", group = KeyCodeGroup.Letters),
    KC_X("X", group = KeyCodeGroup.Letters),
    KC_Y("Y", group = KeyCodeGroup.Letters),
    KC_Z("Z", group = KeyCodeGroup.Letters),
    KC_1("1", group = KeyCodeGroup.Digits),
    KC_2("2", group = KeyCodeGroup.Digits),
    KC_3("3", group = KeyCodeGroup.Digits),
    KC_4("4", group = KeyCodeGroup.Digits),
    KC_5("5", group = KeyCodeGroup.Digits),
    KC_6("6", group = KeyCodeGroup.Digits),
    KC_7("7", group = KeyCodeGroup.Digits),
    KC_8("8", group = KeyCodeGroup.Digits),
    KC_9("9", group = KeyCodeGroup.Digits),
    KC_0("0", group = KeyCodeGroup.Digits),
    KC_ENTER("Enter", group = KeyCodeGroup.Spacing),
    KC_ESCAPE("Escape", group = KeyCodeGroup.Spacing),
    KC_GESC("Grv/Escape", group = KeyCodeGroup.Spacing),
    KC_BSPACE("Backspace", group = KeyCodeGroup.Spacing),
    KC_TAB("Tab", group = KeyCodeGroup.Spacing),
    KC_SPACE("Space", group = KeyCodeGroup.Spacing),
    KC_MINUS("-", group = KeyCodeGroup.Puncuation),
    KC_EQUAL("=", group = KeyCodeGroup.Numpad),
    KC_LBRACKET("[", group = KeyCodeGroup.Puncuation),
    KC_RBRACKET("]", group = KeyCodeGroup.Puncuation),
    KC_BSLASH("\\", group = KeyCodeGroup.Puncuation),          /* \ (and |) */
    KC_NONUS_HASH("Non-US #", group = KeyCodeGroup.Puncuation),      /* Non-US # and ~ (Typically near the Enter key) */
    KC_NONUS_BSLASH("Non-US \\", group = KeyCodeGroup.Puncuation),    /* Non-US \ and | (Typically near the Left-Shift key) */
    KC_SCOLON(";", group = KeyCodeGroup.Puncuation),          /* ; (and :) */
    KC_SCLN(";", group = KeyCodeGroup.Puncuation),
    KC_QUOTE("'", group = KeyCodeGroup.Puncuation),           /* ' and " */
    KC_GRAVE("`", group = KeyCodeGroup.Puncuation),           /* Grave accent and tilde */
    KC_COMMA(",", group = KeyCodeGroup.Puncuation),           /* , and < */
    KC_DOT(".", group = KeyCodeGroup.Puncuation),             /* . and > */
    KC_SLASH("/", group = KeyCodeGroup.Puncuation),           /* / and ? */
    KC_CAPSLOCK("Capslock", group = KeyCodeGroup.Puncuation),
    KC_F1("F1", group = KeyCodeGroup.FnKeys),
    KC_F2("F2", group = KeyCodeGroup.FnKeys),
    KC_F3("F3", group = KeyCodeGroup.FnKeys),
    KC_F4("F4", group = KeyCodeGroup.FnKeys),
    KC_F5("F5", group = KeyCodeGroup.FnKeys),
    KC_F6("F6", group = KeyCodeGroup.FnKeys),
    KC_F7("F7", group = KeyCodeGroup.FnKeys),
    KC_F8("F8", group = KeyCodeGroup.FnKeys),
    KC_F9("F9", group = KeyCodeGroup.FnKeys),
    KC_F10("F10", group = KeyCodeGroup.FnKeys),
    KC_F11("F11", group = KeyCodeGroup.FnKeys),
    KC_F12("F12", group = KeyCodeGroup.FnKeys),
    KC_F13("F13", group = KeyCodeGroup.FnKeys),
    KC_F14("F14", group = KeyCodeGroup.FnKeys),
    KC_F15("F15", group = KeyCodeGroup.FnKeys),
    KC_F16("F16", group = KeyCodeGroup.FnKeys),
    KC_F17("F17", group = KeyCodeGroup.FnKeys),
    KC_F18("F18", group = KeyCodeGroup.FnKeys),
    KC_F19("F19", group = KeyCodeGroup.FnKeys),
    KC_F20("F20", group = KeyCodeGroup.FnKeys),
    KC_F21("F21", group = KeyCodeGroup.FnKeys),
    KC_F22("F22", group = KeyCodeGroup.FnKeys),
    KC_F23("F23", group = KeyCodeGroup.FnKeys),
    KC_F24("F24", group = KeyCodeGroup.FnKeys),
    KC_SYSREQ("SysRq", group = KeyCodeGroup.Navigation),
    KC_PSCREEN("PrtScrn", group = KeyCodeGroup.Navigation),
    KC_SCROLLLOCK("ScrLock", group = KeyCodeGroup.Navigation),
    KC_PAUSE("Pause", group = KeyCodeGroup.Other),
    KC_INSERT("Insert", group = KeyCodeGroup.Navigation),
    KC_HOME("Home", group = KeyCodeGroup.Navigation),
    KC_PGUP("Page Up", group = KeyCodeGroup.Navigation),
    KC_DELETE("Delete", group = KeyCodeGroup.Navigation),
    KC_END("End", group = KeyCodeGroup.Navigation),
    KC_PGDOWN("Page down", group = KeyCodeGroup.Navigation),
    KC_RIGHT("Right", group = KeyCodeGroup.Navigation),
    KC_LEFT("Left", group = KeyCodeGroup.Navigation),
    KC_DOWN("Down", group = KeyCodeGroup.Navigation),
    KC_UP("Up", group = KeyCodeGroup.Navigation),
    KC_NUMLOCK("Numlock", group = KeyCodeGroup.Numpad),
    KC_APPLICATION("App", group = KeyCodeGroup.Navigation),
    KC_POWER("Power", group = KeyCodeGroup.System),
    KC_MENU("Menu", group = KeyCodeGroup.Navigation),
    KC_SELECT("Select", group = KeyCodeGroup.Navigation),
    KC_STOP("Stop", group = KeyCodeGroup.Navigation),
    KC_FIND("Find", group = KeyCodeGroup.Other),
    KC_MUTE("Mute", group = KeyCodeGroup.Media),
    KC_VOLU("Vol +", group = KeyCodeGroup.Media),
    KC_VOLD("Vol -", group = KeyCodeGroup.Media),

    /* Modifiers */
    KC_LCTRL("Left Ctrl", group = KeyCodeGroup.Modifiers),
    KC_LSHIFT("Left Shift", group = KeyCodeGroup.Modifiers),
    KC_LALT("Left Alt", group = KeyCodeGroup.Modifiers),
    KC_LGUI("Left GUI", group = KeyCodeGroup.Modifiers),
    KC_RCTRL("Right Ctrl", group = KeyCodeGroup.Modifiers),
    KC_RSHIFT("Right Shift", group = KeyCodeGroup.Modifiers),
    KC_RALT("Right Alt (AltGr)", group = KeyCodeGroup.Modifiers),
    KC_RGUI("Right GUI", group = KeyCodeGroup.Modifiers),

    /* Mousekey */
    KC_MS_UP("Mouse Up", group = KeyCodeGroup.Mouse),
    KC_MS_DOWN("Mouse Down", group = KeyCodeGroup.Mouse),
    KC_MS_LEFT("Mouse Left", group = KeyCodeGroup.Mouse),
    KC_MS_RIGHT("Mouse Right", group = KeyCodeGroup.Mouse),
    KC_MS_BTN1("Mouse Button 1", group = KeyCodeGroup.Mouse),
    KC_MS_BTN2("Mouse Button 2", group = KeyCodeGroup.Mouse),
    KC_MS_BTN3("Mouse Button 3", group = KeyCodeGroup.Mouse),
    KC_MS_BTN4("Mouse Button 4", group = KeyCodeGroup.Mouse),
    KC_MS_BTN5("Mouse Button 5", group = KeyCodeGroup.Mouse),

    /* Mousekey wheel */
    KC_MS_WH_UP("Mouse Wheel Up", group = KeyCodeGroup.Mouse),
    KC_MS_WH_DOWN("Mouse Wheel Down", group = KeyCodeGroup.Mouse),
    KC_MS_WH_LEFT("Mouse Wheel Left", group = KeyCodeGroup.Mouse),
    KC_MS_WH_RIGHT("Mouse Wheel Right", group = KeyCodeGroup.Mouse),
}