package qianz.frequencyspringbootstarter.util;

import cn.hutool.extra.spring.SpringUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static qianz.frequencyspringbootstarter.context.RedisContexts.REDIS_IP_FREQUENCY_CONTROL;

/**
 * redis工具类
 */
@Component
@AllArgsConstructor
public class RedisUtil {
    private static final StringRedisTemplate stringRedisTemplate;

    static {
        stringRedisTemplate = SpringUtil.getBean(StringRedisTemplate.class);
    }

    /**
     * 从redis中读取
     *
     * @param key  key
     * @param path 路径
     * @return 查找到的结果
     */
    public static String read(String path, Object key) {
        return stringRedisTemplate.opsForValue().get(path + key.toString());

    }

    /**
     * 读取通过url进行频率控制的计数器
     *
     * @param key ip
     * @param url 请求路径
     * @return 查找到的值
     */
    public static String readIpFrequencyControlUri(String url, Object key) {
        return read(REDIS_IP_FREQUENCY_CONTROL + urlToRedisKey(url), key.toString());
    }

    /**
     * 读取通过key进行频率控制的计数器
     *
     * @param ip  ip
     * @param key 关键词
     * @return 查找到的值
     */
    public static String readIpFrequencyControl(String key, Object ip) {
        return read(REDIS_IP_FREQUENCY_CONTROL + key + ":", ip.toString());
    }

    /**
     * 自增key
     *
     * @param key key
     */
    private static void increaseWithTTL(String path, Object key, Integer time, TimeUnit timeUnit) {
        String redisPath = path + key.toString();
        if (stringRedisTemplate.opsForValue().get(redisPath) == null) {
            stringRedisTemplate.opsForValue().increment(redisPath);
            stringRedisTemplate.expire(redisPath, time, timeUnit);
        } else {
            stringRedisTemplate.opsForValue().increment(redisPath);
        }
    }

    /**
     * 自增key的url版
     *
     * @param key 要频控的url拼接用户标识符成为key
     */
    public static void increaseFrequencyUrl(String url, Object key, Integer time, TimeUnit timeUnit) {
        increaseWithTTL(REDIS_IP_FREQUENCY_CONTROL, urlToRedisKey(url) + key.toString(), time, timeUnit);
    }

    /**
     * 自增key的key版
     *
     * @param key 要频控的key拼接用户标识符成为redis key
     */
    public static void increaseFrequency(String key, Object ip, Integer time, TimeUnit timeUnit) {
        increaseWithTTL(REDIS_IP_FREQUENCY_CONTROL, key + ":" + ip.toString(), time, timeUnit);
    }

    /**
     * 将url中的'/'变成':'
     *
     * @param url 网址
     * @return redis key
     */
    private static String urlToRedisKey(String url) {
        if (!isUrl(url)) throw new RuntimeException(url + " 并非url");
        StringBuilder sb = new StringBuilder(url);
        sb.deleteCharAt(0);
        if (sb.charAt(sb.length() - 1) == '}') {
            sb.delete(sb.indexOf("{"), sb.length());
        } else {
            sb.append('/');
        }
        for (int i = 0; i < sb.length(); i++) if (sb.charAt(i) == '/') sb.setCharAt(i, ':');
        return sb.toString();
    }

    /**
     * 将url中的':'变成'/'
     *
     * @param key Redis key
     * @return url
     */
    private static String redisKeyToUrl(String key) {
        if (!isRedisKey(key)) throw new RuntimeException(key + " 并非Redis url");
        StringBuilder sb = new StringBuilder(key);
        sb.insert(0, ':');
        sb.deleteCharAt(sb.length() - 1);
        for (int i = 0; i < sb.length(); i++) if (sb.charAt(i) == ':') sb.setCharAt(i, '/');
        return sb.toString();
    }

    /**
     * string是否为url
     *
     * @param s string
     * @return 是否为url
     */
    private static Boolean isUrl(String s) {
        Pattern pattern = Pattern.compile("(/\\w+)+(/\\{\\w+})?");
        Matcher matcher = pattern.matcher(s);
        return matcher.matches();
    }

    /**
     * string是否为redis key
     *
     * @param s string
     * @return 是否为key
     */
    private static Boolean isRedisKey(String s) {
        Pattern pattern = Pattern.compile("(\\w+:)+:");
        Matcher matcher = pattern.matcher(s);
        return matcher.matches();
    }
}
