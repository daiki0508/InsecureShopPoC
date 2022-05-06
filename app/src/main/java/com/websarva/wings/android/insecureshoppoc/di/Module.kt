package com.websarva.wings.android.insecureshoppoc.di

import com.websarva.wings.android.insecureshoppoc.repository.PlatformRepository
import com.websarva.wings.android.insecureshoppoc.repository.PlatformRepositoryClient
import com.websarva.wings.android.insecureshoppoc.repository.StorageRepository
import com.websarva.wings.android.insecureshoppoc.repository.StorageRepositoryClient
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class Module {
    @Singleton
    @Binds
    abstract fun bindStorageRepository(storageRepositoryClient: StorageRepositoryClient): StorageRepository

    @Singleton
    @Binds
    abstract fun bindPlatformRepository(platformRepositoryClient: PlatformRepositoryClient): PlatformRepository
}