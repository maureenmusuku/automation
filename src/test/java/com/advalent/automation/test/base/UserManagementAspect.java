package com.advalent.automation.test.base;

import com.advalent.automation.api.annotations.documentation.ThreadSafe;
import org.aspectj.lang.annotation.Aspect;

/**
 * Releases a user held by a test as soon as a
 * test class requests a different user, or after
 * the test is complete
 *
 * @author sshrestha
 */

@Aspect
@ThreadSafe
public class UserManagementAspect {
  /*  private final Logger logger = LoggerFactory.getLogger(UserManagementAspect.class);
    private final Map<BaseTest, User> acquiredUsers = new ConcurrentHashMap<>();

    @Around("execution(com.vh.mi..dto.User com.vh.mi..BaseTest.getUser(..))")
    public User onRequestUser(ProceedingJoinPoint thisJoinPoint) throws Throwable {
        BaseTest testInstance = (BaseTest) thisJoinPoint.getTarget();
        if (acquiredUsers.containsKey(testInstance)) {
            UserManager.INSTANCE.releaseUser(acquiredUsers.get(testInstance));
        }
        User user = (User) thisJoinPoint.proceed();
        acquiredUsers.put(testInstance, user);
        logger.info("{} acquired user {} ", testInstance.getClass().getSimpleName(), user);
        return user;
    }

    @Before("execution(* com.vh..BaseTest.tearDownTestClass(..))")
    public void beforeTearDownTestClass(JoinPoint thisJoinPoint) {
        BaseTest testInstance = (BaseTest) thisJoinPoint.getTarget();
        if (acquiredUsers.containsKey(testInstance)) {
            User user = acquiredUsers.get(testInstance);
            testInstance.releaseUser(user);
            acquiredUsers.remove(testInstance);
            logger.info("{} released user {} ", testInstance.getClass().getSimpleName(), user);
        }
    }*/
}
