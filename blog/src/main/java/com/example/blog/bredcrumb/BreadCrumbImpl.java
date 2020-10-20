package com.example.blog.bredcrumb;

import com.example.blog.core.TitleManager;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Component
public class BreadCrumbImpl implements BreadCrumb {

    private final HttpServletRequest request;
    private final TitleManager titleManager;

    public BreadCrumbImpl(HttpServletRequest request, TitleManager titleManager) {
        this.request = request;
        this.titleManager = titleManager;
    }

    @Override
    public Crumb[] getCrumbList() {
        String requestURI = request.getRequestURI();
        String[] split = requestURI.split("/");
        ArrayList<Crumb> result = new ArrayList<>();
        String address = "";
        for (int i = 1; i < split.length; i++) {
            String linkPart = split[i];
            String name = getTitleByLink(linkPart);
            address = address + "/" + linkPart;
            Crumb crumb = new Crumb(name, address);
            if (i == split.length - 1) {
                crumb.setLast(true);
            }
            result.add(crumb);
        }
        return result.toArray(new Crumb[result.size()]);
    }

    private String getTitleByLink(String link) {
        String result = "title not found";
        if (titleManager.getLangMap().containsKey(link)) {
            result = titleManager.getLangMap().get(link);
        } else result = "";

        return result;
    }

}
