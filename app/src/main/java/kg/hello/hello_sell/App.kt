package kg.hello.hello_sell

import android.app.Application
import kg.hello.hello_sell.di.module.dataModule
import kg.hello.hello_sell.di.module.repoModule
import kg.hello.hello_sell.di.module.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@App)
            modules(dataModule, repoModule, viewModelModule)
        }
    }
}