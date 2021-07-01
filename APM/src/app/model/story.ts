export class Story {
    id: number;
    is_highlighted: boolean;
    start_time: Date;
    pathOfContent:string;
    tag:string;
    isVideo:boolean
}

export class StoryGroup {
    story1: Story;
    story2: Story;
    story3: Story;
    name = "ASD";
}
