import { BrowserModule } from '@angular/platform-browser';
import {Component, NgModule} from '@angular/core';
import {RouterModule, Routes, RoutesRecognized} from '@angular/router';
import {APP_BASE_HREF, HashLocationStrategy, LocationStrategy} from '@angular/common';
import { AboutComponent } from './about/about.component';
import { HomeComponent } from './home/home.component';
import { ContactComponent } from './contact/contact.component';
import { AppComponent } from './app.component';

@Component({
  // tslint:disable-next-line:component-selector
  selector: 'router-app',
  template: `<div>
    <nav>
      <a>Navigation:</a>
      <ul>
        <li><a [routerLink]="['home']">Home</a></li>
        <li><a [routerLink]="['about']">About</a></li>
        <li><a [routerLink]="['contact']">Contact Us</a></li>
      </ul>
    </nav>
    <router-outlet></router-outlet>
  </div>
  `
})
// tslint:disable-next-line:component-class-suffix
export class RoutesDemoApp {
  title = 'lesson7';
}

const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'about', component: AboutComponent },
  { path: 'contact', component: ContactComponent },
  { path: 'contactus', redirectTo: 'contact' },
];


// @ts-ignore
// @ts-ignore
@NgModule({
  declarations: [
    RoutesDemoApp,
    AppComponent,
    HomeComponent,
    AboutComponent,
    ContactComponent
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(routes) // 安装路由 <--routes
  ],
  providers: [
    // HashLocationStrategy AngularJs的模式基于锚点标记策略。解析的地址为/#/home
    { provide: LocationStrategy, useClass: HashLocationStrategy },
    { provide: APP_BASE_HREF, useValue: '/'}
    ],
  bootstrap: [AppComponent]
})
export class AppModule { }

