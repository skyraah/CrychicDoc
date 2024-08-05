package dev.ftb.mods.ftblibrary.integration.stages;

import dev.ftb.mods.ftblibrary.FTBLibrary;
import java.util.Objects;

public enum StageHelper {

    INSTANCE;

    private static final StageProvider FALLBACK = new EntityTagStageProvider();

    private StageProvider activeImpl = null;

    public static StageHelper getInstance() {
        return INSTANCE;
    }

    public void setProviderImpl(StageProvider newProvider) {
        if (this.activeImpl != null) {
            FTBLibrary.LOGGER.warn("Overriding existing game stages provider: {} -> {}", this.activeImpl.getName(), newProvider.getName());
        } else {
            FTBLibrary.LOGGER.info("Setting game stages provider implementation to: {}", newProvider.getName());
        }
        this.activeImpl = newProvider;
    }

    public StageProvider getProvider() {
        return (StageProvider) Objects.requireNonNullElse(this.activeImpl, FALLBACK);
    }
}