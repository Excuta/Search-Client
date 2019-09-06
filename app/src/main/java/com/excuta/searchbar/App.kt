package com.excuta.searchbar

import android.app.Application
import com.excuta.searchbar.domain.di.DaggerDomainComponent
import com.excuta.searchbar.presentation.di.DaggerPresentationComponent
import com.excuta.searchbar.presentation.di.PresentationComponent

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