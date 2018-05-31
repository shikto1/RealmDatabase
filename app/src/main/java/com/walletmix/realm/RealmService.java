package com.walletmix.realm;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmService {

    private Realm realm = null;

    private RealmService() {
    }

    public static RealmService sharedService = new RealmService();


    //Open Realm Database................................
    public void openRealm() {
        try {
            realm = Realm.getDefaultInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Add new Student..............................................
    public void addStudent(Student student) {
        openRealm();
        realm.beginTransaction();

        realm.copyToRealm(student);

        realm.commitTransaction();
        closeRealm();
    }

    //Update student.................................
    public void updateStudent(final int studentId, final Student updatedstudent) {
        openRealm();
        realm.beginTransaction();

        Student currentStudent = realm.where(Student.class).equalTo("studentId", studentId).findFirst();
        if (currentStudent != null) {
            currentStudent.setStudentName(updatedstudent.getStudentName());
            currentStudent.setStudentAge(updatedstudent.getStudentAge());
        }

        realm.commitTransaction();
        closeRealm();
    }

    // Delete student...............................
    public void delete(final int studentId) {
        openRealm();
        realm.beginTransaction();

        Student student = realm.where(Student.class).equalTo("studentId", studentId).findFirst();
        student.deleteFromRealm();

        realm.commitTransaction();
        closeRealm();
    }


    // Clear Realm Database....................
    public void clearRealmDatabase() {
        openRealm();
        realm.beginTransaction();

        realm.where(Student.class).findAll().deleteAllFromRealm();

        realm.commitTransaction();
        closeRealm();
    }

    // Close Realm Database...........
    public void closeRealm() {
        realm.close();
    }


    //Refresh Realm Database...........
    public void refreShDB() {
        realm.refresh();
    }

    //Get Student with id...........
    public Student getStuentWithId(int stuendtId) {
        return realm.where(Student.class).equalTo("studentId", stuendtId).findFirst();
    }

    //Get All Student.......................
    public RealmResults<Student> getAllStudents() {
        RealmResults<Student> studentList = realm.where(Student.class).findAllAsync();
        studentList.load();
        return studentList;
    }

}
