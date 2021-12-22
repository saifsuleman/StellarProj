# stellar project
This is a project that allows you to copy and paste builds - while saving them to a file without using schematics.

This plugin utilises YAML, and in order to achieve placing in order from the bottom upwards, it saves the builds from the very bottom to the top, and as YAML supports ordered lists, we know it will be placed like this.

Also saves build asynchronously for added performance.

## commands
- `/getwand` - gives you the selection wand that allows you to make selections by clicking opposite corners.
- `/savebuild <name>` - saves your selection into a file with the correct name you specified.
- `/placebuild <name>` - places a build that has been saved