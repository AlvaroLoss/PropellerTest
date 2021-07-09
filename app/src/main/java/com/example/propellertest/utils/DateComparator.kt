package com.example.propellertest.utils

import com.example.propellertest.api.model.EventsItem
import com.example.propellertest.utils.DateUtils.format

class DateComparator: Comparator<EventsItem> {
    override fun compare(o1: EventsItem?, o2: EventsItem?): Int {
        if(o1 == null || o2 == null){
            return 0;
        }

        val date1 = format.parse(o1.datetime)
        val date2 = format.parse(o2.datetime)

        return if (date1.before(date2))
            1
        else
            -1
    }
}