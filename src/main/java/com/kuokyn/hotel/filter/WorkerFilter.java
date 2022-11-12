package com.kuokyn.hotel.filter;

import org.springframework.util.StringUtils;

public class WorkerFilter {

    private String phrase;

    public boolean isEmpty() {
        return StringUtils.isEmpty(phrase);
    }

    public void clear() {
        this.phrase = "";
        //  this.minPrice = null;
        // this.maxPrice = null;
    }

    public String getPhraseLIKE() {

        if (phrase.equals("")) {
            return null;
        } else {
            return "%" + phrase + "%";
        }

    }

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }


}
