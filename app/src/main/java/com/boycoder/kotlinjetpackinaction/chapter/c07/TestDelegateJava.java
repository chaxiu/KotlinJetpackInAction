package com.boycoder.kotlinjetpackinaction.chapter.c07;

/**
 * Created by zhu.tao on 2020/8/6.
 */
class TestDelegateJava {
    public static void main(String[] args) {

    }

    class UniversalDB implements DB {
        DB db;
        public UniversalDB(DB db) { this.db = db; }

        @Override
        public void save() { db.save(); }
    }
}
