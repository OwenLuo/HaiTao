package com.lvcaiye.haitao.share.core;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author owen
 */
public class ShareFactory {

    public static Map<Class, ShareDisplayer> map = new LinkedHashMap<Class, ShareDisplayer>();

    public static ShareDisplayer getShareDisplayer(Class<? extends ShareDisplayer> cls)
            throws InstantiationException, IllegalAccessException {
        ShareDisplayer share = map.get(cls);
        if (share == null) {
            synchronized (ShareFactory.class) {
                if (share == null) {
                    register(cls.newInstance());
                }
            }
        }
        return map.get(cls);
    }

    private static void register(ShareDisplayer share) {
        map.put(share.getClass(), share);
    }

    public static Collection<ShareDisplayer> getAll() {
        return map.values();
    }
}
