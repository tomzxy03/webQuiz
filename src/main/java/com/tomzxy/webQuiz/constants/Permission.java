package com.tomzxy.webQuiz.constants;

public class Permission {
    public static final String ADMIN = "ADMIN.GRANTED";
    public static final String BASIC = "BASIC.PUBLIC";
    public static final String DASHBOARD = "DASHBOARD";

    public static class System{
        public static class User{
            public static final String VIEW = "SYSTEM.USER.VIEW";
            public static final String CREATE = "SYSTEM.USER.CREATE";
            public static final String UPDATE = "SYSTEM.USER.UPDATE";
            public static final String DELETE = "SYSTEM.USER.DELETE";
        }
        public static class Role {
            public static final String VIEW = "SYSTEM.ROLE.VIEW";
            public static final String CREATE = "SYSTEM.ROLE.CREATE";
            public static final String UPDATE = "SYSTEM.ROLE.UPDATE";
            public static final String DELETE = "SYSTEM.ROLE.DELETE";
        }
        public static class Manager_Question{
            public static final String VIEW = "MANAGER.QUESTION.VIEW";
            public static final String CREATE = "MANAGER.QUESTION.CREATE";
            public static final String UPDATE = "MANAGER.QUESTION.UPDATE";
            public static final String DELETE = "MANAGER.QUESTION.DELETE";
            public static final String FILE_UPLOAD = "MANAGER.QUESTION.FILE";
        }
        public static class MANAGER_ANSWER{
            public static final String VIEW = "MANAGER.ANSWER.VIEW";
            public static final String CREATE = "MANAGER.ANSWER.CREATE";
            public static final String UPDATE = "MANAGER.ANSWER.UPDATE";
            public static final String DELETE = "MANAGER.ANSWER.DELETE";

        }


    }

    public static class MANAGER_QUIZ{
        public static final String VIEW = "MANAGER.QUIZ.VIEW";
        public static final String CREATE = "MANAGER.QUIZ.CREATE";
        public static final String UPDATE = "MANAGER.QUIZ.UPDATE";
        public static final String DELETE = "MANAGER.QUIZ.DELETE";
    }
    public static class MANAGER_QUIZ_RESULT{
        public static final String VIEW = "MANAGER.QUIZ_RESULT.VIEW";
        public static final String CREATE = "MANAGER.QUIZ_RESULT.CREATE";
        public static final String UPDATE = "MANAGER.QUIZ_RESULT.UPDATE";
        public static final String DELETE = "MANAGER.QUIZ_RESULT.DELETE";
    }
    
}
