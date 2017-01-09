package com.zillionfortune.sdk;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigException;
import com.typesafe.config.ConfigFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by king on 7/19/16.
 */
public class GrowingConfig {
    private static final Logger logger = LoggerFactory.getLogger(GrowingConfig.class);

    // 必须设置四个基础参数
    private final String ai;
    private final String projectId;
    private final String secretKey;
    private final String publicKey;

    private Config config;

    public GrowingConfig() {
        this("growingApi");
    }

    public GrowingConfig(String configName) {
        try {
            this.config = ConfigFactory.load(configName);
            ai = this.config.getString("app.ai");
            projectId = this.config.getString("app.projectId");
            secretKey = this.config.getString("app.secretKey");
            publicKey = this.config.getString("app.publicKey");
        } catch (ConfigException.Missing e) {
            logger.error("Configuration Error: " + configName);
            throw new ConfigException.Missing("Missing Required Configuration: " + e);
        }
    }

    public String getAi() {
        return ai;
    }

    public String getProjectId() {
        return projectId;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public String baseStorePath() {
        String path = "/tmp";
        try {
            path = config.getString("app.store");
        } catch (ConfigException.Missing e) {
            logger.info("use default store path: /tmp/${type}/${date}/part-xxxxx");
        }
        return path;
    }

    public boolean uncompress() {
        boolean uncompress = false;
        try {
            uncompress = config.getBoolean("app.uncompress");
        } catch (ConfigException.Missing e) {
            logger.info("not uncompress the downloaded file");
        }
        return uncompress;
    }
}
