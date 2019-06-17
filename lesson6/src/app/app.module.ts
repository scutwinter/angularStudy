import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SimpleHTTPComponent } from './ts/SimpleHTTPComponent';

@NgModule({
  declarations: [
    AppComponent,
    SimpleHTTPComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [SimpleHTTPComponent]
})
export class AppModule { }
