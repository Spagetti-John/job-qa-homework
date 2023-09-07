package org.janis.qa.homework.helpers;

public class FileHelper {

    public static String getFileNameForForTest(String fileName) {
        var nameParts = fileName.split("\\.");

        var extension = nameParts[nameParts.length - 1];

        var filePath = new StringBuilder();
        for(int i = 0; i < nameParts.length - 1; i++) {
            filePath.append(nameParts[i]);
        }

        filePath.append(TestCaseContext.getTestTestCaseID());
        filePath.append(".");
        filePath.append(extension);

        return filePath.toString();
    }
}
