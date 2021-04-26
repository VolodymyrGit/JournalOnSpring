package volm.journal.comparators;

import volm.journal.model.Homework;

import java.util.Comparator;

public class HomeworkComparator implements Comparator<Homework> {

    @Override
    public int compare(Homework o1, Homework o2) {
        return  o1.getLesson().getLessonDate().compareTo(o2.getLesson().getLessonDate());
    }
}
