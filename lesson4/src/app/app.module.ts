  import { BrowserModule } from '@angular/platform-browser';
  import { NgModule } from '@angular/core';

  import { AppRoutingModule } from './app-routing.module';
  import { AppComponent } from './app.component';

  import { NgSwitchSampleApp } from './ng_switch/ng_switch';
  import { NgSwitchSampleAppModule } from './ng_switch/ng_switch';
  import { NgStyleSampleApp } from './ng_style/ng_style';
  import { NgStyleSampleAppModule } from './ng_style/ng_style';

  import { NgClassSampleApp} from './ng_class/ng_class';
  import { NgClassSampleAppModule} from './ng_class/ng_class';

  import { NgForSampleApp } from './ng_for/ng_for';
  import { NgForSampleAppModule } from './ng_for/ng_for';

  @NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgSwitchSampleAppModule,
    NgStyleSampleAppModule,
    NgClassSampleAppModule,
    NgForSampleAppModule
  ],
  providers: [],
  bootstrap: [NgSwitchSampleApp, NgStyleSampleApp , NgClassSampleApp , NgForSampleApp]
})
export class AppModule { }

