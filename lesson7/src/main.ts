import { enableProdMode } from '@angular/core';
import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';

import { AppModule } from './app/app.module';
import { environment } from './environments/environment';

import { MusicDemoAppModule } from './app/music/app';

if (environment.production) {
  enableProdMode();
}

platformBrowserDynamic().bootstrapModule(MusicDemoAppModule)
  .catch(err => console.error(err));
