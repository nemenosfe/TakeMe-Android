package com.pes.takemelegends.Controller;

public class ControllerFactory {

    private static ControllerFactory instance;
    private EventController eventController;
    private AchievementController achievementController;
    private UserController userController;
    private RewardController rewardController;

    public static ControllerFactory getInstance() {
        if (instance == null) {
            instance = new ControllerFactory();
        }
        return instance;
    }

    public EventController getEventController() {
        if (eventController == null) eventController = new EventController();
        return eventController;
    }
    public RewardController getRewardController() {
        if (rewardController == null) rewardController = new RewardController();
        return rewardController;
    }

    public AchievementController getAchievementController() {
        if (achievementController == null) achievementController = new AchievementController();
        return achievementController;
    }

    public UserController getUserController() {
        if (userController == null) userController = new UserController();
        return userController;
    }
}
