import { BrowserModule } from '@angular/platform-browser';
import {Component, NgModule} from '@angular/core';
import {RouterModule, Routes, RoutesRecognized} from '@angular/router';
import {APP_BASE_HREF, HashLocationStrategy, LocationStrategy} from '@angular/common';
import { AboutComponent } from './about/about.component';
import { HomeComponent } from './home/home.component';
import { ContactComponent } from './contact/contact.component';

const routes: Routes = [
  {path: '', redirectTo: 'home',pathMatch: 'full' },
  {path: 'home', component: HomeComponent },
  {path: 'about', component: AboutComponent },
  {path: 'contact', component: ContactComponent },
  {path: 'contactus', redirectTo: 'contact'},
];

@Component({
  // tslint:disable-next-line:component-selector
  selector: 'router-app',
  template: `<div>
    <nav>
      <a>Navigation:</a>
      <ul>
        <li><a [routerLink]="['home']" ]>Home</a></li>
        <li><a [routerLink]="['about']">About</a></li>
        <li><a [routerLink]="['Contact']">Contact Us</a></li>
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

// @ts-ignore
// @ts-ignore
@NgModule({
  declarations: [
    RoutesDemoApp,
    HomeComponent,
    AboutComponent,
    ContactComponent
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(routes) // 安装路由 <--routes
  ],
  providers: [
    { provide: LocationStrategy, useClass: HashLocationStrategy },
    { provide: APP_BASE_HREF, useValue: '/'}
    ],
  bootstrap: [RoutesDemoApp]
})
export class AppModule { }

