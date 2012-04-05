public static Bitmap getVideoThumbnail(ContentResolver cr, String fileName) {
                Bitmap bitmap = null;
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inDither = false;
                options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                //select condition.
                String whereClause = MediaStore.Video.Media.DATA + " = '"
                        + fileName + "'";
                Log.v(TAG, "where = " + whereClause);
                //colection of results.
                Cursor cursor = cr.query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                                new String[] { MediaStore.Video.Media._ID }, whereClause,
                                null, null);
                Log.v(TAG, "cursor = " + cursor);
                if (cursor == null || cursor.getCount() == 0) {
                        return null;
                }
                cursor.moveToFirst();
                //image id in image table.
                String videoId = cursor.getString(cursor
                                .getColumnIndex(MediaStore.Video.Media._ID));
                Log.v(TAG, "videoId = " + videoId);
                if (videoId == null) {
                        return null;
                }
                cursor.close();
                long videoIdLong = Long.parseLong(videoId);
                //via imageid get the bimap type thumbnail in thumbnail table.
                bitmap = MediaStore.Video.Thumbnails.getThumbnail(cr, videoIdLong,
                                Images.Thumbnails.MICRO_KIND, options);
                Log.v(TAG, "bitmap = " + bitmap);
                return bitmap;
        }