import { Component } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';



@Component({
  // tslint:disable-next-line:component-selector
  selector: 'more-http',
  template: `
    <h2>More Requests</h2>
    <button type="button" class="btn btn-info" (click)="makePost()">Make Post</button>
    <button type="button" class="btn btn-info" (click)="makeDelete()">Make Delete</button>
    <button type="button" class="btn btn-info" (click)="makeHeaders()">Make Headers</button>
    <div *ngIf="loading">loading...</div>
    <pre>{{data | json}}</pre>
  `
})
// tslint:disable-next-line:component-class-suffix
export class MoreHTTPRequests {
  data: Object;
  loading: boolean;

  constructor(private http: HttpClient) {
  }
  makePost(): void {
    this.loading = true;
    this.http.post(
      'http://jsonplaceholder.typicode.com/posts',
      JSON.stringify({
        body: 'bar',
        title: 'foo',
        userId: 1
      })
    ).subscribe((res) => {
      this.data = res;
      this.loading = false;
    });
  }
  makeDelete(): void{
    this.loading = true;
    this.http.delete('http://jsonplaceholder.typicode.com/posts/1')
      .subscribe(res => {
        this.data = res;
        this.loading  = false;
      });
  }

  makeHeaders(): void{
    let header: HttpHeaders = new HttpHeaders();
    header.append('X-API-TOKEN', 'ng-book');

    let options = {
      headers: header
    };
    // @ts-ignore
    this.http.get('http://jsonplaceholder.typicode.com/posts/1', options)
      .subscribe(res => {
        this.data = res;
      });
  }
}
