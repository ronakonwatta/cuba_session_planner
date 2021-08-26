package com.company.planner.web.screens.speaker;

import com.haulmont.cuba.gui.screen.*;
import com.company.planner.entity.Speaker;

@UiController("planner_Speaker.edit")
@UiDescriptor("speaker-edit.xml")
@EditedEntityContainer("speakerDc")
@LoadDataBeforeShow
public class SpeakerEdit extends StandardEditor<Speaker> {
}