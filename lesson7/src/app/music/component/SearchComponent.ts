import { Component, OnInit} from '@angular/core';
import {
  Router,
  ActivatedRoute,
} from '@angular/router';

import {SpotifyService} from '../services/SpotifyService';

@Component({
  // tslint:disable-next-line:component-selector
  selector: 'search',
  template: `
  <h1>Search</h1>

  <p>
    <input type="text" #newquery
           [value]="query"
           (keydown.enter)="submit(newquery.value)">
    <button class="btn btn-info" (click)="submit(newquery.value)">Search</button>
  </p>
  <div *ngIf="results">
    <div *ngIf="!results.length">
      No tracks were found with the term '{{ query }}'
    </div>
    <div *ngIf="results.length">
      <h1>Results</h1>
      <div class="row">
        <div class="col-sm-6 col-md-4" *ngFor="let t of results">
          <div class="thumbnail">
            <div class="content">
<!--              <img src="{{ t.artists[0].img1v1Url }}" class="img-responsive">-->
              <div class="caption">
                <h3>
                  <a [routerLink]="['/song',t.id]">
                    {{ t.name }}
                  </a>
                </h3>
                <br>
                <p>
                  <a>
                    {{ t.artists[0].name }}
                  </a>
                </p>
              </div>
              <div class="attribution">
                <h4>
                  <a [routerLink]="['/albums', t.id]">
                    {{ t.album.name }}
                  </a>
                </h4>
              </div>
            </div>
          </div>
          <audio src={{t.playAddress}} controls="controls">
            Your browser does not support the audio tag.
          </audio>
        </div>
      </div>
    </div>
  </div>
  `
})
export class SearchComponent implements OnInit {
  query: string ;
  results: Object;

  constructor(private spotify: SpotifyService,
              private router: Router,
              private route: ActivatedRoute) {
    this.route.queryParams.subscribe(
      params => { this.query = params['query'] || ''; });
  }

  ngOnInit(): void {
    this.search();
  }
  submit(query: string): void {
    this.router.navigate(['search'], { queryParams: { query: query }})
      .then(_ => this.search());
  }
  search(): void {
    console.log('this.query',this.query);
    if (!this.query) {
      return;
    }
    this.spotify.searchTrack(this.query)
      .subscribe((res: any) => {
        this.renderResults(res);
        },
        (err: any) => {
          alert(err.error.error.message.toString());
        },
        () => {
        }
        );
  }

  renderResults(res: any): void {
    this.results =  null;
    if (res && res.result && res.result.songs) {
      this.results = res.result.songs;
    }
    // tslint:disable-next-line:prefer-const
    let count: number = 0 ;
    // tslint:disable-next-line:forin
    // count = Object.keys(this.results).length;
    this.getPlayAddress(this.results , count);
    // this.spotify.getAddress(this.results[0].id)
    //   .subscribe((res: any) => {
    //       while (count <= Object.keys(this.results).length) {
    //         console.log( res.data[0].url);
    //         this.results[count].playAddress = res.data[0].url;
    //         count++;
    //       }
    //
    //     },
    //     (err: any) => {
    //       alert(err.error.error.message.toString());
    //     },
    //     () => {
    //     }
    //   );

  }

  getPlayAddress(songResult: any, icount: number){
    if (icount < Object.keys(songResult).length ) {
      this.spotify.getAddress(songResult[icount].id)
        .subscribe((res: any) => {
            console.log( res.data[0].url);
            songResult[icount].playAddress = res.data[0].url;
            icount++;
            this.getPlayAddress(songResult, icount);
          },
          (err: any) => {
            alert(err.error.error.message.toString());
          },
          () => {
          }
        );
    }

  }
}
