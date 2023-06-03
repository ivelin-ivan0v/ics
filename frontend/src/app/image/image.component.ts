import { Component, Input, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-add-image',
  templateUrl: './image.component.html',
  styleUrls: ['./image.component.scss'],
})
export class ImageComponent implements OnInit {
  imageId: string = '';
  imageInfo: any;
  constructor(private http: HttpClient, private route: ActivatedRoute, private router:Router) {}
  ngOnInit(): void {
    this.route.params.subscribe((params) => {
      this.imageId = params['id'];
      this.getImageInfo(this.imageId);
    });
  }

  getImageInfo(imageId: string) {
    this.http
      .get('http://localhost:8080/images/' + imageId)
      .subscribe((response) => {
        this.imageInfo = response;
        console.log(this.imageInfo);
      });
  }

  onClick(tag: any){
    this.router.navigate(['/gallery'], {queryParams: {tags:tag.tagName}});
  }
}
