import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';



@Component({
  // tslint:disable-next-line:component-selector
  selector: 'more-http',
  template: `
    <h2>More Requests</h2>
    <button type="button" class="btn btn-info" (click)="makePost()">Make Post</button>
    <button type="button" class="btn btn-info" (click)="makeDelete()">Make Delete</button>
    <button type="button" class="btn btn-info" (click)="makeHeadersyonyou()">Make Headers</button>
    <div *ngIf="loading">loading...</div>
    <pre>{{message}}</pre>
    <pre>{{data | json}}</pre>
  `
})
// tslint:disable-next-line:component-class-suffix
export class MoreHTTPRequests implements OnInit {
  data: Object;
  message: string;
  loading: boolean;
  strCode: string;

  constructor(private http: HttpClient) {
  }

  ngOnInit(): void {
    console.log(this.getParameterByName('code'));
    this.strCode = this.getParameterByName('code');
    this.getAccesstoken();

  }

  getParameterByName(name): string {
    name = name.replace(/[\[]/, '\\\[').replace(/[\]]/, '\\\]');
    const regex = new RegExp('[\\?&]' + name + '=([^&#]*)'),
      results = regex.exec(location.search);
    return results == null ? '' : decodeURIComponent(results[1]);
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
  makeDelete(): void {
    this.loading = true;
    this.http.delete('http://jsonplaceholder.typicode.com/posts/1')
      .subscribe(res => {
        this.data = res;
        this.loading  = false;
      });
  }
  //
  // makeHeaders(): void{
  //   let header: HttpHeaders = new HttpHeaders();
  //   header.append('token', 'ng-book');
  //   let options = {
  //     headers: header
  //   };
  //   const httpOptions = {
  //     headers: new HttpHeaders({
  //       'Content-Type':  'application/json',
  //       'Authorization': 'my-auth-token',
  //       'toker':'ng-book'
  //     })
  //   };
  //   // @ts-ignore
  //   this.http.get('http://jsonplaceholder.typicode.com/posts/1', httpOptions)
  //     .subscribe(res => {
  //       this.data = res;
  //     });
  // }

  makeHeadersyonyou(): void {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',
        'X-EisExternLogin-Code': this.strCode
      })
    };
    // @ts-ignore
    this.http.get('https://link.yonyouup.com/eisexternallogin/upesn', httpOptions)
      .subscribe(res => {
        this.data = res;
      });
  }

  getAccesstoken(): void {
    this.loading = true;
    this.http.get('http://mywinterday.com:23389/api/MyStudy/getAppToken')
      .subscribe((res) => {
        // tslint:disable-next-line:no-unused-expression
        window.localStorage.setItem('access_token', res.toString());
        console.log(res.toString());
        this.freelogin();
        this.loading = false;
      });
  }
  freelogin(): void {
    this.loading = true;
    this.http.get('http://mywinterday.com:23389/api/MyStudy/freeLogin?accessToken=' +
      window.localStorage.getItem('access_token') + '&strcode=' + this.strCode)
      .subscribe((res) => {
        this.data = res;
        // tslint:disable-next-line:triple-equals
        if ((this.data as any).msg == 'success') {
          this.message = '免登成功';
        } else {
          this.message = '免登失败';
        }
        console.log('result:' + this.data );
        this.loading = false;
      });
  }
}
