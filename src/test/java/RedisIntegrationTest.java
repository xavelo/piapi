import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

//@RunWith(SpringRunner.class)
public class RedisIntegrationTest {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void testRedisOperations() {
        redisTemplate.opsForValue().set("foo", "bar");
        Object value = redisTemplate.opsForValue().get("foo");
        assertNotNull(value);
        System.out.println(value);

    }
}

