package com.boycoder.kotlinjetpackinaction.chapter.c05;

/**
 * Created by zhu.tao on 2020/7/29.
 */
class JavaGenerics {

    public static void main(String[] args) {
        useSiteCovariant();
        useSiteContravariant();
    }

    public static void useSiteCovariant() {
        University<? extends Student> covariant = new University<FemaleStudent>("女子大学");
        Student student = covariant.get();
        // 报错
        // covariant.put(student);
    }

    public static void useSiteContravariant() {
        University<? super FemaleStudent> contravariant = new University<Student>("普通大学");
        contravariant.put(new FemaleStudent());
        // 报错
        // Student s = contravariant.get();
    }
}


