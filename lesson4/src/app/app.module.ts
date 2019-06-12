  import { BrowserModule } from '@angular/platform-browser';
  import { NgModule } from '@angular/core';

  import { AppRoutingModule } from './app-routing.module';
  import { AppComponent } from './app.component';

  import { NgSwitchSampleApp } from './ng_switch/ng_switch';
  import { NgSwitchSampleAppModule } from './ng_switch/ng_switch';

  @NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgSwitchSampleAppModule
  ],
  providers: [],
  bootstrap: [NgSwitchSampleApp]
})
export class AppModule { }

