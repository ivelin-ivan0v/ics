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
  isPortrait: boolean = false;

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

        // Determine the image orientation based on height and width
        const imageHeight = this.imageInfo.image.height;
        const imageWidth = this.imageInfo.image.width;
        this.isPortrait = imageHeight > imageWidth;
      });
  }

  onClick(tag: any){
    this.router.navigate(['/gallery'], {queryParams: {tags:tag.tagName}});
  }
}
