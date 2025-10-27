# html2image-demo

``` java
@page {
			size: A4;
			margin: 2.5cm 1.8cm 2cm 1.9cm;
			@bottom-left {
				content: element(footer);
			}
			@top-left {
				content: element(header);
			}
			@top-left-corner {
				content: element(topleftcorner);
			}
			@left-middle {
				content: element(left);
			}
			@bottom-right {
				content: element(bottomright);
			}
		}
		
		
@page {
  size: 100px 100px;
  margin: 20px;
}



@page {
  size: 180px 100px;
  margin: 18px;
  -fs-max-overflow-pages: 10;
  -fs-overflow-pages-direction: ltr;
  
  @top-left {
    content: "Page " counter(page) -fs-if-cut-off(" (continued)") " of " counter(pages);
    font-family: 'TestFont';
    font-size: 12px;
  }
}
```
