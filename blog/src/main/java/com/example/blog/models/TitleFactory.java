package com.example.blog.models;

import com.example.blog.core.TitleManager;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class TitleFactory {

    private final HttpServletRequest request;
    private final TitleManager titleManager;

    public TitleFactory(HttpServletRequest request, TitleManager titleManager) {
        this.request = request;
        this.titleManager = titleManager;
    }

    public String getTitle() {
        String requestURI = request.getRequestURI();
        String[] linksMassive = requestURI.split("/");
        return getTitleByLink(linksMassive[linksMassive.length - 1]);
    }

    private String getTitleByLink(String link) {
        String result = "Title not found";
        result = titleManager.getLangMap().get(link);
        return result;
    }

}
