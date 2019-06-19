import { Component , Injectable , OnInit , ElementRef , EventEmitter , Inject} from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { fromEvent } from 'rxjs';
import { debounceTime, filter, map, switchAll, tap} from 'rxjs/operators';


export var YOUTUBE_API_KEY: string = 'AIzaSyBuAQNNkVtxRAXKur786q-H8SO_hekNqds';
export var YOUTUBE_API_URL: string = 'https://www.googleapis.com/youtube/v3/search';
// @ts-ignore
let loadingGif: string = ((<any>window).__karma__) ? '' : require('images/loading.gif');

class SearchResult {
  id: string;
  title: string;
  description: string;
  thumbnailUrl: string;
  videoUrl: string;

  constructor(obj?: any) {
    this.id = obj && obj.id || null;
    this.title = obj && obj.title || null;
    this.description = obj && obj.description || null;
    this.thumbnailUrl = obj && obj.thumbnailUrl || null;
    this.videoUrl = obj && obj.videoUrl || `https://www.youtube.com/watch?v=${this.id}`; // 字符串插值
  }
}


@Injectable()
export class YouTubeService {
  constructor(private http: HttpClient,
              @Inject(YOUTUBE_API_KEY) private apiKey: string,
              @Inject(YOUTUBE_API_URL) private apiUrl: string) {

  }
  search(query: string): Observable<SearchResult[]> {
    let params: string = [
      `q=${query}`,
      `key=${this.apiKey}`,
      `part=snippet`,
      `type=video`,
      `maxResults=10`
    ].join('&');
    let queryUrl: string = `${this.apiUrl}?${params}`;
    return this.http.get(queryUrl)
      .pipe(map((res) => {
        return (<any> res).items.map(item => {
          return new SearchResult({
            id: item.id.videoId,
            title: item.snippet.title,
            description: item.snippet.description,
            thumbnailUrl: item.snippet.thumbnails.high.url
          });
        });
      })
      );

  }
}
export var youTubeServiceInjecttables: Array <any> = [
  { provide: YouTubeService, useClass: YouTubeService},
  { provide: YOUTUBE_API_KEY, useValue: YOUTUBE_API_KEY},
  { provide: YOUTUBE_API_URL, useValue: YOUTUBE_API_URL}
];

@Component({
  // tslint:disable-next-line:no-outputs-metadata-property
  outputs: ['loading', 'results'],
  // tslint:disable-next-line:component-selector
  selector: 'search-box',
  template: `
  <input type="text" class="form-control" placeholder="Search" autofocus>
  `
})
// tslint:disable-next-line:component-class-suffix
export class SearchBox implements OnInit {
  loading: EventEmitter<boolean> = new EventEmitter<boolean>();
  results: EventEmitter<SearchResult[]> = new EventEmitter<SearchResult[]>();
  constructor(private youtube: YouTubeService,
              private el: ElementRef ) {
  }
  ngOnInit(): void {
    // 老的rxjs是使用Observable.fromEvent 新的直接使用fromEvent
    // 从rxjs-operators中引入您需要的操作符
    // 使用管道操作而不是链式操作
    /*
    注意：由于与Javascript保留字冲突，以下运算符名字做了修改：do -> tap, catch ->
     catchError, switch -> switchAll, finally -> finalize
     */
    fromEvent(this.el.nativeElement, 'keyup')
      .pipe(
        map((e: any) => e.target.value),
        filter((text: string) => text.length > 1),
        debounceTime(250),
        tap(() => this.loading.next(true)),
        map((query: string) => this.youtube.search(query)),
        switchAll()
      ).subscribe(
      (results: SearchResult[]) => {
        this.loading.next(false);
        this.results.next(results);
      },
      (err: any) => {
        console.log(err);
        this.loading.next(false);
      },
      () => {
        this.loading.next(false);
      }
    );
  }
}
@Component({
  // tslint:disable-next-line:no-inputs-metadata-property
  inputs: ['result'],
  // tslint:disable-next-line:component-selector
  selector: 'search-result',
  template: `
  <div class="col-sm-6 col-md-3">
    <div class="thumbnail">
      <img src="{{result.thumbnailUrl}}">
      <div class="caption">
        <h3>{{result.title}}</h3>
        <p>{{result.description}}</p>
        <p><a href="{{result.videoUrl}}"
              class="btn btn-default" role="button">Watch</a>
        </p>
      </div>
    </div>
  </div>`
})
export class SearchResultComponent{
  result: SearchResult;
}

@Component({
  // tslint:disable-next-line:component-selector
  selector: 'youtube-search',
  template: `
  <div class='container'>
    <div class="page-header">
      <h1>YouTube Search
        <img style="float: right;"
             *ngIf="loading"
             src='${loadingGif}' />
      </h1>
    </div>

    <div class="row">
      <div class="input-group input-group-lg col-md-12">
        <search-box
        (loading)="loading = $event"
        (results)="updateResults($event)"
        ></search-box>
      </div>
    </div>
    <div class="row">
      <search-result
      *ngFor="let result of results"
      [result]="result">
      </search-result>
    </div>
  </div>
`
})
export class YouTubeSearchComponent{
  results: SearchResult[];
  updateResults(results: SearchResult[]): void {
    this.results = results;
  }
}
