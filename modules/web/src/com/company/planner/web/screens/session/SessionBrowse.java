package com.company.planner.web.screens.session;

import com.company.planner.service.SessionService;
import com.haulmont.cuba.gui.components.Calendar;
import com.haulmont.cuba.gui.components.EditorScreenFacet;
import com.haulmont.cuba.gui.components.TextField;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.screen.*;
import com.company.planner.entity.Session;

import javax.inject.Inject;
import java.time.LocalDateTime;

@UiController("planner_Session.browse")
@UiDescriptor("session-browse.xml")
@LookupComponent("sessionsTable")
@LoadDataBeforeShow
public class SessionBrowse extends StandardLookup<Session> {

    @Inject
    private EditorScreenFacet<Session, SessionEdit> sessionEditDialog;
    @Inject
    private SessionService sessionService;
    @Inject
    private CollectionContainer<Session> sessionsDc;
    @Inject
    private TextField<String> txt01;

    @Subscribe("sessionsCalendar")
    public void onSessionsCalendarCalendarEventClick(Calendar.CalendarEventClickEvent<LocalDateTime> event) {
        sessionEditDialog.setEntityProvider(()-> (Session) event.getEntity());
        sessionEditDialog.show();
    }

    @Subscribe("sessionsCalendar")
    public void onSessionsCalendarCalendarEventMove(Calendar.CalendarEventMoveEvent<LocalDateTime> event) {
        Session session = sessionService.rescheduleSession((Session) event.getEntity(), event.getNewStart());
        sessionsDc.replaceItem(session);
    }

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
//        String res = "OK";
        String res = sessionService.showSomething();
        txt01.setValue(res);


    }


}