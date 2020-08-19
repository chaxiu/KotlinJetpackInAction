package com.boycoder.kotlinjetpackinaction.chapter.c09.java;

/**
 * Created by zhu.tao on 2020/8/11.
 */

class TestAsync {
    public static void main(String[] args) {
        testAsync();
        testCallBackHell();
    }

    public static void testAsync() {
        getUserInfo(new CallBack() {
            @Override
            public void onSuccess(String response) {
                if (response != null) {
                    System.out.println(response);
                }
            }
        });
    }

    public static void testCallBackHell() {
        getUserInfo(new CallBack() {
            @Override
            public void onSuccess(String user) {
                if (user != null) {
                    System.out.println(user);
                    getFriendList(user, new CallBack() {
                        @Override
                        public void onSuccess(String friendList) {
                            if (friendList != null) {
                                System.out.println(friendList);
                                getFeedList(friendList, new CallBack() {
                                    @Override
                                    public void onSuccess(String feed) {
                                        if (feed != null) {
                                            System.out.println(feed);
                                        }
                                    }
                                });
                            }
                        }
                    });
                }
            }
        });
    }

    public static void getUserInfo(CallBack callBack) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000L);
                    callBack.onSuccess("BoyCoder");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void getFriendList(String user, CallBack callBack) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000L);
                    callBack.onSuccess("Tom, Jack");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void getFeedList(String user, CallBack callBack) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000L);
                    callBack.onSuccess("FeedList");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
