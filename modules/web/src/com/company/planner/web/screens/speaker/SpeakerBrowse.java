package com.company.planner.web.screens.speaker;

import com.haulmont.cuba.gui.screen.*;
import com.company.planner.entity.Speaker;

@UiController("planner_Speaker.browse")
@UiDescriptor("speaker-browse.xml")
@LookupComponent("speakersTable")
@LoadDataBeforeShow
public class SpeakerBrowse extends StandardLookup<Speaker> {
}