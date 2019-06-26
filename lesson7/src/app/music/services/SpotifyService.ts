import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map} from 'rxjs/operators';


@Injectable()
export class SpotifyService {
  // static BASE_URL = 'https://api.spotify.com/v1';
  static BASE_URL = 'http://localhost:4500';
  constructor(private http: HttpClient) {
  }
  query(URL: string, params?: Array<string>): Observable<any[]> {
    let queryURL: string = `${SpotifyService.BASE_URL}${URL}`;
    if (params) {
      queryURL = `${queryURL}?${params.join('&')}`;
    }
    console.log(queryURL);
    // @ts-ignore
    return this.http.get(queryURL).pipe(map((res) => res));
  }

  search(query: string, type: string): Observable<any[]> {
      return  this.query(`/search`, [
        `keywords= ${query}`,
        `type=${type}`
      ]);
  }

  searchTrack(query: string): Observable<any[]> {
    return this.search(query, '1');
  }
  getAddress(id: string): Observable<any[]> {
    return this.query(`/song/url?id=${id}`);
  }

  getTrack(id: string): Observable<any[]> {
    return this.query(`/tracks/${id}`);
  }
  getArtist(id: string): Observable<any[]> {
    return this.query(`/artists/${id}`);
  }
  getAlbum(id: string): Observable<any[]> {
    return this.query(`/albums/${id}`);
  }
}

export let SPOTIFY_PROVIDERS: Array<any> = [
  {provide: SpotifyService, useClass: SpotifyService}
];
