import { Component , Injectable , OnInit , ElementRef , EventEmitter , Inject} from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { fromEvent } from 'rxjs';
import {debounceTime, filter, map, switchAll, tap} from 'rxjs/operators';


export var YOUTUBE_API_KEY: string = 'AIzaSyBuAQNNkVtxRAXKur786q-H8SO_hekNqds';
export var YOUTUBE_API_URL: string = 'https://www.googleapis.com/youtube/v3/search';
// @ts-ignore
let loadingGif: string = ((<any> window).__karma__) ? '' : require('images/loading.gif');

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
    this.videoUrl = obj && obj.videoUrl || `https://www.youtube.com/watch?=${this.id}`; // 字符串插值
  }
}


@Injectable()
export class YouTubeService {
  constructor(private http: HttpClient,
              @Inject(YOUTUBE_API_KEY) private apiKey: string,
              @Inject(YOUTUBE_API_URL) private apiUrl: string) {

  }
  search(query: string): Observable<SearchResult[]>{
    let params: string = [
      `q=${query}`,
      `key=${this.apiKey}`,
      `part=snippet`,
      `type=video`,
      `maxResults=10`
    ].join('&');
    let queryUrl: string = `${this.apiUrl}?${params}`;
    return this.http.get(queryUrl)
      .map((response: Response) => {
        return (<any>response.json()).items.map(item => {
          return new SearchResult({
            id: item.id.videoId,
            title: item.snippet.title,
            description: item.snippet.description,
            thumbnailUrl: item.snippet.thumbnails.high.url
          });
        });
      });
  }
}
export var youTubeServiceInjecttables: Array<any> = [
  {provide: YouTubeService, useClass: YouTubeService},
  {provide: YOUTUBE_API_KEY, useClass: YOUTUBE_API_KEY},
  {provide: YOUTUBE_API_URL, useClass: YOUTUBE_API_URL}
];

@Component({
  outputs: ['loading','results'],
  selector: 'search-box',
  template: `
  <input type="text" class="form-control" placeholder="Search" autofocus>
  `
})
export class SearchBox implements OnInit{
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
      ).subscribe()
  }


}

