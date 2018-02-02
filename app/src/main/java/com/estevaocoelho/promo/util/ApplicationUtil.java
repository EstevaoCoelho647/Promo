package com.estevaocoelho.promo.util;

import android.content.Context;

/**
 * Created by Adenilson on 4/24/2017.
 */

public class ApplicationUtil {
    private static Context context;

    private ApplicationUtil(){
        super();
    }

    public static void setContext(Context context){
        ApplicationUtil.context = context;
    }

    public static Context getContext(){
        return ApplicationUtil.context;
    }
}
