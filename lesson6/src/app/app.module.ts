import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SimpleHTTPComponent } from './ts/SimpleHTTPComponent';
import {SearchBox, SearchResultComponent, YouTubeSearchComponent} from './ts/YouTubeSearchComponent';

import {youTubeServiceInjecttables} from './ts/YouTubeSearchComponent';

@NgModule({
  declarations: [
    AppComponent,
    SimpleHTTPComponent,
    YouTubeSearchComponent,
    SearchBox,
    SearchResultComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule
  ],
  bootstrap: [SimpleHTTPComponent, YouTubeSearchComponent],
  providers: [youTubeServiceInjecttables]
})
export class AppModule { }
