/*
 * Angular Imports
 */
import {
  Component
} from '@angular/core';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';
import { HttpClientModule } from '@angular/common/http';


import {
  RouterModule,
  Routes
} from '@angular/router';
import {
  LocationStrategy,
  HashLocationStrategy,
  APP_BASE_HREF
} from '@angular/common';

/*
 * Components
 */
import {SearchComponent} from './component/SearchComponent';
// import {ArtistComponent} from 'components/ArtistComponent';
// import {TrackComponent} from 'components/TrackComponent';
// import {AlbumComponent} from 'components/AlbumComponent';

/*
 * Services
 */
import {SPOTIFY_PROVIDERS} from './services/SpotifyService';

// /*
//  * Webpack
//  */
// require('css/styles.css');

@Component({
  selector: 'music-app',
  template: `
  <router-outlet></router-outlet>
  `
})
export class MusicDemoApp {
  query: string;
}

const routes: Routes = [
  { path: '', redirectTo: 'search', pathMatch: 'full' },
  { path: 'search', component: SearchComponent },
  // { path: 'artists/:id', component: ArtistComponent },
  // { path: 'tracks/:id', component: TrackComponent },
  // { path: 'albums/:id', component: AlbumComponent },
];

@NgModule({
  declarations: [
    MusicDemoApp,
    SearchComponent
    // ArtistComponent,
    // TrackComponent,
    // AlbumComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    RouterModule.forRoot(routes) // <-- routes
  ],
  bootstrap: [ MusicDemoApp ],
  providers: [
    SPOTIFY_PROVIDERS,
    {provide: APP_BASE_HREF, useValue: '/'},
    {provide: LocationStrategy, useClass: HashLocationStrategy}
  ]
})
export class MusicDemoAppModule {}

platformBrowserDynamic().bootstrapModule(MusicDemoAppModule)
  .catch((err: any) => console.error(err));
