package com.excuta.searchclient

import android.app.Application
import com.excuta.searchclient.domain.di.DaggerDomainComponent
import com.excuta.searchclient.presentation.di.DaggerPresentationComponent
import com.excuta.searchclient.presentation.di.PresentationComponent

class App : Application() {
    val presentationComponent: PresentationComponent by lazy {
        DaggerPresentationComponent.builder()
            .domainComponent(
                DaggerDomainComponent.builder()
                    .build()
            )
            .build()
    }
}